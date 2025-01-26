package com.wisnitech.omiesales.data.source.remote.source

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wisnitech.omiesales.data.model.Customer

internal class CustomerRemoteDataSourceImpl : CustomerRemoteDataSource {

    override suspend fun loadCustomers(): List<Customer> {
        val listType = object : TypeToken<List<Customer>>() {}.type

        val json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Felipe Wisniewski\",\n" +
                "        \"phone\": \"48991585801\",\n" +
                "        \"registerDate\": \"02/01/2025\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"André Fonseca\",\n" +
                "        \"phone\": \"43991585801\",\n" +
                "        \"registerDate\": \"02/01/2025\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Henrique Gomes\",\n" +
                "        \"phone\": \"48991585001\",\n" +
                "        \"registerDate\": \"03/01/2025\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"Jorge Mota\",\n" +
                "        \"phone\": \"48991580000\",\n" +
                "        \"registerDate\": \"04/01/2025\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"name\": \"Flávio Mansur\",\n" +
                "        \"phone\": \"51991585801\",\n" +
                "        \"registerDate\": \"02/01/2025\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"name\": \"Edmundo da Silva\",\n" +
                "        \"phone\": \"48992585801\",\n" +
                "        \"registerDate\": \"02/01/2025\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 7,\n" +
                "        \"name\": \"Debora Oliveira\",\n" +
                "        \"phone\": \"48991535303\",\n" +
                "        \"registerDate\": \"02/01/2025\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "        \"name\": \"Walesca Matos\",\n" +
                "        \"phone\": \"48991585804\",\n" +
                "        \"registerDate\": \"02/01/2025\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 9,\n" +
                "        \"name\": \"Rosana Silva\",\n" +
                "        \"phone\": \"48991383831\",\n" +
                "        \"registerDate\": \"02/01/2025\"\n" +
                "    }\n" +
                "]"

        return Gson().fromJson(json, listType)
    }

}