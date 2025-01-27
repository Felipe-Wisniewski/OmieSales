package com.wisnitech.omiesales.data.source.remote.source

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wisnitech.omiesales.data.model.Product

internal class ProductRemoteDataSourceImpl : ProductRemoteDataSource {

    override suspend fun loadProducts(): List<Product> {
        val listType = object : TypeToken<List<Product>>() {}.type

        val json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"code\": \"P0001\",\n" +
                "        \"name\": \"Coca-Cola Lata 350ml\",\n" +
                "        \"price\": 4.50,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"bebidas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"code\": \"P0002\",\n" +
                "        \"name\": \"Suco de Laranja Natural 300ml\",\n" +
                "        \"price\": 6.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"bebidas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"code\": \"P0003\",\n" +
                "        \"name\": \"Água Mineral 500ml\",\n" +
                "        \"price\": 3.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"bebidas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"code\": \"P0004\",\n" +
                "        \"name\": \"Picanha com Acompanhamentos\",\n" +
                "        \"price\": 79.90,\n" +
                "        \"priceUnit\": \"Kg\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"code\": \"P0005\",\n" +
                "        \"name\": \"Macarronada ao Sugo\",\n" +
                "        \"price\": 25.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"massas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"code\": \"P0006\",\n" +
                "        \"name\": \"Espaguete à Carbonara\",\n" +
                "        \"price\": 30.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"massas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 7,\n" +
                "        \"code\": \"P0007\",\n" +
                "        \"name\": \"Salada Mista com Frango\",\n" +
                "        \"price\": 18.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "        \"code\": \"P0008\",\n" +
                "        \"name\": \"Sorvete de Chocolate\",\n" +
                "        \"price\": 8.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 9,\n" +
                "        \"code\": \"P0009\",\n" +
                "        \"name\": \"Petit Gateau com Sorvete\",\n" +
                "        \"price\": 15.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 10,\n" +
                "        \"code\": \"P0010\",\n" +
                "        \"name\": \"Café Expresso\",\n" +
                "        \"price\": 4.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"bebidas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 11,\n" +
                "        \"code\": \"P0011\",\n" +
                "        \"name\": \"Filé de Frango Grelhado\",\n" +
                "        \"price\": 25.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 12,\n" +
                "        \"code\": \"P0012\",\n" +
                "        \"name\": \"Torta de Limão\",\n" +
                "        \"price\": 12.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 13,\n" +
                "        \"code\": \"P0013\",\n" +
                "        \"name\": \"Risoto de Camarão\",\n" +
                "        \"price\": 35.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 14,\n" +
                "        \"code\": \"P0014\",\n" +
                "        \"name\": \"Panqueca de Carne\",\n" +
                "        \"price\": 22.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 15,\n" +
                "        \"code\": \"P0015\",\n" +
                "        \"name\": \"Pizza Margherita\",\n" +
                "        \"price\": 40.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"massas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 16,\n" +
                "        \"code\": \"P0016\",\n" +
                "        \"name\": \"Brownie com Sorvete\",\n" +
                "        \"price\": 14.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 17,\n" +
                "        \"code\": \"P0017\",\n" +
                "        \"name\": \"Lasanha Bolonhesa\",\n" +
                "        \"price\": 28.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"massas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 18,\n" +
                "        \"code\": \"P0018\",\n" +
                "        \"name\": \"Cheesecake de Frutas Vermelhas\",\n" +
                "        \"price\": 16.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 19,\n" +
                "        \"code\": \"P0019\",\n" +
                "        \"name\": \"Frango à Parmegiana\",\n" +
                "        \"price\": 32.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 20,\n" +
                "        \"code\": \"P0020\",\n" +
                "        \"name\": \"Salada Caesar\",\n" +
                "        \"price\": 20.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 21,\n" +
                "        \"code\": \"P0021\",\n" +
                "        \"name\": \"Espresso Macchiato\",\n" +
                "        \"price\": 5.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"bebidas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 22,\n" +
                "        \"code\": \"P0022\",\n" +
                "        \"name\": \"Sanduíche Natural de Atum\",\n" +
                "        \"price\": 12.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 23,\n" +
                "        \"code\": \"P0023\",\n" +
                "        \"name\": \"Pão de Alho Recheado\",\n" +
                "        \"price\": 6.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 24,\n" +
                "        \"code\": \"P0024\",\n" +
                "        \"name\": \"Creme de Papaia com Cassis\",\n" +
                "        \"price\": 18.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 25,\n" +
                "        \"code\": \"P0025\",\n" +
                "        \"name\": \"Chocolate Quente\",\n" +
                "        \"price\": 7.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"bebidas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 26,\n" +
                "        \"code\": \"P0026\",\n" +
                "        \"name\": \"Camarão à Milanesa\",\n" +
                "        \"price\": 48.00,\n" +
                "        \"priceUnit\": \"Kg\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 27,\n" +
                "        \"code\": \"P0027\",\n" +
                "        \"name\": \"Hambúrguer Artesanal\",\n" +
                "        \"price\": 22.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 28,\n" +
                "        \"code\": \"P0028\",\n" +
                "        \"name\": \"Tiramisu Tradicional\",\n" +
                "        \"price\": 14.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 29,\n" +
                "        \"code\": \"P0029\",\n" +
                "        \"name\": \"Panqueca de Frango\",\n" +
                "        \"price\": 20.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 30,\n" +
                "        \"code\": \"P0030\",\n" +
                "        \"name\": \"Canja de Galinha\",\n" +
                "        \"price\": 15.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 31,\n" +
                "        \"code\": \"P0031\",\n" +
                "        \"name\": \"Croissant de Presunto e Queijo\",\n" +
                "        \"price\": 8.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 32,\n" +
                "        \"code\": \"P0032\",\n" +
                "        \"name\": \"Brigadeiro Gourmet\",\n" +
                "        \"price\": 3.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 33,\n" +
                "        \"code\": \"P0033\",\n" +
                "        \"name\": \"Pastel de Carne\",\n" +
                "        \"price\": 7.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 34,\n" +
                "        \"code\": \"P0034\",\n" +
                "        \"name\": \"Filé Mignon ao Molho Madeira\",\n" +
                "        \"price\": 38.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 35,\n" +
                "        \"code\": \"P0035\",\n" +
                "        \"name\": \"Bolo de Cenoura com Chocolate\",\n" +
                "        \"price\": 5.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 36,\n" +
                "        \"code\": \"P0036\",\n" +
                "        \"name\": \"Empada de Frango\",\n" +
                "        \"price\": 4.50,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 37,\n" +
                "        \"code\": \"P0037\",\n" +
                "        \"name\": \"Crepe de Queijo\",\n" +
                "        \"price\": 6.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 38,\n" +
                "        \"code\": \"P0038\",\n" +
                "        \"name\": \"Sopa de Legumes\",\n" +
                "        \"price\": 12.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 39,\n" +
                "        \"code\": \"P0039\",\n" +
                "        \"name\": \"Milkshake de Morango\",\n" +
                "        \"price\": 10.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"bebidas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 40,\n" +
                "        \"code\": \"P0040\",\n" +
                "        \"name\": \"Limonada Suíça\",\n" +
                "        \"price\": 8.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"bebidas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 41,\n" +
                "        \"code\": \"P0041\",\n" +
                "        \"name\": \"Omelete de Queijo\",\n" +
                "        \"price\": 10.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 42,\n" +
                "        \"code\": \"P0042\",\n" +
                "        \"name\": \"Batata Frita\",\n" +
                "        \"price\": 15.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 43,\n" +
                "        \"code\": \"P0043\",\n" +
                "        \"name\": \"Frappuccino de Café\",\n" +
                "        \"price\": 12.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"bebidas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 44,\n" +
                "        \"code\": \"P0044\",\n" +
                "        \"name\": \"Pizza de Calabresa\",\n" +
                "        \"price\": 42.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"massas\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 45,\n" +
                "        \"code\": \"P0045\",\n" +
                "        \"name\": \"Cuscuz com Carne de Sol\",\n" +
                "        \"price\": 18.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 46,\n" +
                "        \"code\": \"P0046\",\n" +
                "        \"name\": \"Mousse de Maracujá\",\n" +
                "        \"price\": 10.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 47,\n" +
                "        \"code\": \"P0047\",\n" +
                "        \"name\": \"Esfirra de Carne\",\n" +
                "        \"price\": 6.50,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 48,\n" +
                "        \"code\": \"P0048\",\n" +
                "        \"name\": \"Hot Dog Completo\",\n" +
                "        \"price\": 12.00,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 49,\n" +
                "        \"code\": \"P0049\",\n" +
                "        \"name\": \"Frutas da Estação\",\n" +
                "        \"price\": 8.00,\n" +
                "        \"priceUnit\": \"Kg\",\n" +
                "        \"category\": \"almoço\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 50,\n" +
                "        \"code\": \"P0050\",\n" +
                "        \"name\": \"Cupcake Red Velvet\",\n" +
                "        \"price\": 7.50,\n" +
                "        \"priceUnit\": \"Un\",\n" +
                "        \"category\": \"sobremesa\"\n" +
                "    }\n" +
                "]"

        return Gson().fromJson(json, listType)
    }
}