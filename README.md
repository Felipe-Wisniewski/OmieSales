# Omie Sales

Aplicativo Android de vendas desenvolvido em Kotlin.

## Requisitos:
- O aplicativo deve permitir que o usuário registre um pedido com vários itens, incluindo a descrição de cada item, a quantidade, o valor unitário e o valor total de cada item.
- O aplicativo deve calcular o valor total do pedido somando o valor total de cada item.
- O aplicativo deve permitir que o usuário visualize todos os pedidos registrados, incluindo a descrição dos itens do pedido, a quantidade, o valor unitário e o valor total de cada item, bem como o valor total do pedido.

## Biblíotecas utilizadas:

- [Room](https://developer.android.com/jetpack/androidx/releases/room) : Persistência de dados no SQLite.
- [Coroutines](https://developer.android.com/kotlin/coroutines) | [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) | [Flow](https://developer.android.com/kotlin/flow): Tarefas assincronas.
- [Koin](https://github.com/InsertKoinIO/koin) : Injeção de dependências.
- [Materia Desing](https://m3.material.io/components) : Componentes de UI.
- [MockK](https://mockk.io/) : Criar mocks (dubles) para realização de testes.

## TODO
- Finalizar testes unitários.
- Criar testes instrumentados.
- Extrair textos hardcoded para string.xml
- Agrupar produtos por categoria