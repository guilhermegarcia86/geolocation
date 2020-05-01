package com.challenge.geolocation.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.challenge.geolocation.codec.EstabelecimentoCodec;
import com.challenge.geolocation.filter.Filter;
import com.challenge.geolocation.model.Estabelecimento;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

@Repository
public class EstabelecimentoRepository {

	private MongoClient client;
	private MongoDatabase mongoDataBase;

	@Value("${host}")
	private String host;

	@Value("${port}")
	private String port;

	@Value("${database}")
	private String database;

	@Value("${collection.estabelecimento}")
	private String estabelecimento;

	private MongoCollection<Estabelecimento> openConnetion() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);

		EstabelecimentoCodec estCodec = new EstabelecimentoCodec(codec);

		CodecRegistry registry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(estCodec));

		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(registry).build();

		this.client = new MongoClient(host + ":" + port, options);
		this.mongoDataBase = client.getDatabase(database);

		return this.mongoDataBase.getCollection(this.estabelecimento, Estabelecimento.class);
	}

	private void closeConnection() {
		this.client.close();
	}

	private List<Estabelecimento> fillEstabelecimento(MongoCursor<Estabelecimento> resultados) {
		List<Estabelecimento> estabelecimentos = new ArrayList<>();
		while (resultados.hasNext()) {
			estabelecimentos.add(resultados.next());
		}
		return estabelecimentos;
	}

	public List<Estabelecimento> searchByGeolocation(Filter filter) {
		try {
			MongoCollection<Estabelecimento> estabelecimentoCollection = openConnetion();

			estabelecimentoCollection.createIndex(Indexes.geo2dsphere("localizacao"));

			Point referencePoint = new Point(new Position(filter.getLat(), filter.getLng()));

			MongoCursor<Estabelecimento> resultados = estabelecimentoCollection
					.find(Filters.nearSphere("localizacao", referencePoint, filter.getDistance(), 0.0)).limit(filter.getLimit()).iterator();

			List<Estabelecimento> estabelecimentos = fillEstabelecimento(resultados);

			return estabelecimentos;
		} finally {
			closeConnection();
		}
	}

	public List<Estabelecimento> findAll() {
		try {
			MongoCollection<Estabelecimento> estabelecimentoCollection = openConnetion();

			MongoCursor<Estabelecimento> resultados = estabelecimentoCollection.find().iterator();

			List<Estabelecimento> estabelecimentos = fillEstabelecimento(resultados);

			return estabelecimentos;

		} finally {
			closeConnection();
		}

	}

	public void save(Estabelecimento estabelecimento) {
		try {
			MongoCollection<Estabelecimento> estabelecimentoCollection = openConnetion();
			if (estabelecimento.getId() == null) {
				estabelecimentoCollection.insertOne(estabelecimento);
			} else {
				estabelecimentoCollection.updateOne(Filters.eq("_id", estabelecimento.getId()),
						new Document("$set", estabelecimento));
			}
		} finally {
			closeConnection();
		}
	}
}
