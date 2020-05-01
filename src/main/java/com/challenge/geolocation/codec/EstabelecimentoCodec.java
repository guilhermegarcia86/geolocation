package com.challenge.geolocation.codec;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import com.challenge.geolocation.model.Estabelecimento;
import com.challenge.geolocation.model.Localizacao;


public class EstabelecimentoCodec implements CollectibleCodec<Estabelecimento>{
	
	private Codec<Document> codec;
	
	public EstabelecimentoCodec(Codec<Document> codec) {
		this.codec = codec;
	}

	@Override
	public void encode(BsonWriter writer, Estabelecimento estabelecimento, EncoderContext encoder) {
		Document document = new Document();
		
		document.put("_id", estabelecimento.getId());
		document.put("nome", estabelecimento.getNome());
		document.put("email", estabelecimento.getEmail());
		
		Localizacao localizacao = estabelecimento.getLocalizacao();
		
		List<Double> coordinates = new ArrayList<>();
		localizacao.getCoordinates().forEach(coordinates::add);
		
		document.put("localizacao", new Document()
				.append("endereco", localizacao.getEndereco())
				.append("coordinates", coordinates)
				.append("type", localizacao.getType()));
		
		codec.encode(writer, document, encoder);
	}

	@Override
	public Class<Estabelecimento> getEncoderClass() {
		return Estabelecimento.class;
	}

	@Override
	public Estabelecimento decode(BsonReader reader, DecoderContext decoderContext) {
		
		Document document = codec.decode(reader, decoderContext);
		
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId(document.getObjectId("_id"));
		estabelecimento.setNome(document.getString("nome"));
		estabelecimento.setEmail(document.getString("email"));
		
		Document localizacao = (Document) document.get("localizacao");
		if(localizacao != null) {
			String endereco = localizacao.getString("endereco");
			@SuppressWarnings("unchecked")
			List<Double> coordinates = (List<Double>) localizacao.get("coordinates");
			
			Localizacao localizacaoEntity = new Localizacao();
			localizacaoEntity.setEndereco(endereco);
			localizacaoEntity.setCoordinates(coordinates);
			
			estabelecimento.setLocalizacao(localizacaoEntity);
		}
		
		return estabelecimento;
	}

	@Override
	public Estabelecimento generateIdIfAbsentFromDocument(Estabelecimento estabelecimento) {
		return documentHasId(estabelecimento) ? estabelecimento.generateId() : estabelecimento;
	}

	@Override
	public boolean documentHasId(Estabelecimento estabelecimento) {
		return estabelecimento.getId() == null;
	}

	@Override
	public BsonValue getDocumentId(Estabelecimento estabelecimento) {
		if (!documentHasId(estabelecimento)) {
			throw new IllegalStateException("This Document have not a id");
		}
		
		return new BsonString(estabelecimento.getId().toHexString());
	}

}
