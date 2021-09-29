*Languages: [en](README.en.md)*

# Insplash

Insplash é um aplicativo desenvolvido na linguagem Kotlin para Android utilizando o Jetpack Compose UI Toolkit para construção da interface e consome a API do [Unsplash](https://unsplash.com/) que é um repositório de imagens gratuitas.

O app possui uma tela de listagem de fotos com opção de pesquisa, uma tela para exibir a foto + informações e uma tela para listagem das fotos favoritas. Possui uma funcionalidade para favoritar fotos que serão salvas somente no banco de dados local por questões de simplicidade. 

A arquitetura definida segue recomendações e princípios da documentação oficial do Android e baseia-se na estrutura conforme imagem abaixo. 

![image](https://user-images.githubusercontent.com/2924219/132413978-d6026326-ed73-4956-9e47-0515938a8f96.png)

## Características do código e bibliotecas utilizadas

* Construção de UI utilizando o Jetpack Compose. 
* Arquitetura MVVM com ViewModel para interação com repositório e UI
* Camada de dados utilizando repositório + data sources
* Coroutines para programação assíncrona e gerenciamento de longas tarefas
* Cliente HTTP e configuração de conexão da API com Retrofit
* Injeção de dependência com Dagger Hilt
* Carregamento e caching de imagens com Glide
* Armazenamento de resultados da API no banco de dados local com Room
* Mecanismo de paginação de resultados implementado manualmente
* Criação de classes genéricas auxiliares a fim de evitar repetições de código (princípios de DRY code)

## Como configurar

Para executar o projeto no Android Studio, antes é necessário criar uma chave de acesso à API pelo portal de desenvolvedor do [Unsplash](https://unsplash.com/developers). Após cadastro, serão gerados uma chave de acesso e uma chave secreta que deverão ser adicionadas ao arquivo `local.properties` conforme exemplo:

`unsplashApi.accessKey="your_access_key"`<br/>
`unsplashApi.secretKey="your_secret_key"`

**O projeto está pronto para ser executado! :)**
