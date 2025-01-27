# Omie Sales
Aplicativo Android de vendas desenvolvido em Kotlin.

## Requisitos
- O aplicativo permite que o usuário registre um pedido com vários itens, incluindo a descrição de cada item, a quantidade, o valor unitário e o valor total de cada item.
- O aplicativo calcula o valor total do pedido somando o valor total de cada item.
- O aplicativo permite que o usuário visualize todos os pedidos registrados, incluindo a descrição dos itens do pedido, a quantidade, o valor unitário e o valor total de cada item, bem como o valor total do pedido.

## Bibliotecas utilizadas
- [Room](https://developer.android.com/jetpack/androidx/releases/room) : Persistência de dados no SQLite.
- [Coroutines](https://developer.android.com/kotlin/coroutines) | [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) | [Flow](https://developer.android.com/kotlin/flow): Tarefas assíncronas.
- [Koin](https://github.com/InsertKoinIO/koin) : Injeção de dependências.
- [Materia Desing](https://m3.material.io/components) : Componentes de UI.
- [MockK](https://mockk.io/) | [Mockito](https://site.mockito.org/): Criar mocks (dubles) para realização de testes.

## Arquitetura
- O App esta modularizado separando as camadas de apresentação e dados.<br>
 [:APP] -> [:UI] -> [:DATA] 
<br><br>
- O módulo :app contém a MainActivity (single activity) com a NavHostFragment que controla a navegação dos navGraph.
- O módulo :ui Contém os Fragmentos com as lógicas de apresentação de UI e as ViewModels com as lógicas de negócio, os graphos de navegação, algumas classes utilitárias.
- O módulo :data contém os repositórios, fonte de dados local e remoto e as data classes, somente os repositórios estão visivéis para camada :ui.
- Com a aplicação separada por camadas, utilizando design patterns como MVVM, Repository, Observers, Singleton, injeção de dependência com Koin, orientado a interfaces que reduz o acoplamento, garante um projeto organizado e escalável, de facíl manutenção e testabilidade.

## Base de dados
- Foi utilizado um banco SQLite que faz toda a persistência dos dados local do aplicativo, existe uma chamada remota que esta mockada, ela retorna uma lista de produtos que é persistido localmente.
<br><br>
- Diagrama do banco:

<img src="https://github.com/Felipe-Wisniewski/OmieSales/blob/feature-images/media/db-diagram.png" width="800"/>

## Preview
<img src="https://github.com/Felipe-Wisniewski/OmieSales/blob/feature-images/media/omiesales.gif" width="300"/>

## TODO
- Finalizar testes unitários.
- Criar testes instrumentados.
- Extrair textos hardcoded para string.xml
- Agrupar produtos por categoria
- Adicionar imagens dos produtos.
- Melhorar fluxo cadastro cliente.
- *Refatorar UI para Jetpack Compose.
