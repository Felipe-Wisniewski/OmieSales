package com.wisnitech.omiesales.data.source.remote.source

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wisnitech.omiesales.data.model.Sale

internal class SaleRemoteDataSourceImpl : SaleRemoteDataSource {

    override suspend fun loadSales(): List<Sale> {
        val listType = object : TypeToken<List<Sale>>() {}.type

        val json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"customerId\": 1,\n" +
                "        \"totalItems\": 5,\n" +
                "        \"totalSale\": 1.5,\n" +
                "        \"saleDate\": \"02/01/2025\",\n" +
                "        \"productsId\": [\n" +
                "            41,\n" +
                "            42,\n" +
                "            43,\n" +
                "            44,\n" +
                "            45\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"customerId\": 2,\n" +
                "        \"totalItems\": 3,\n" +
                "        \"totalSale\": 1.5,\n" +
                "        \"saleDate\": \"02/01/2025\",\n" +
                "        \"productsId\": [\n" +
                "            1,\n" +
                "            2,\n" +
                "            3\n" +
                "        ]\n" +
                "    }\n" +
                "]"

        return Gson().fromJson(json, listType)
    }
}