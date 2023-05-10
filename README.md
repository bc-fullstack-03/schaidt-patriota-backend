# Projeto Backend 🦜 Parrot

Bem-vindo ao Projeto Backend 🦜 Parrot, uma API de rede social desenvolvida em Java com o Framework Spring.

## 🧑‍💻 Desenvolvedor

- [Schaidt Patriota](https://www.linkedin.com/in/schaidt-patriota-ab8b13202/)

## 📜 Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).

## 🚀 Introdução

O Projeto Backend 🦜 Parrot é uma API robusta e eficiente que permite a gestão de usuários, publicações e interações em uma plataforma de rede social. Desenvolvido como parte do Programa Trainee SysMap de Excelência Full Stack, essa API foi implementada em Java com o uso do Framework Spring, proporcionando segurança, desempenho e escalabilidade.

## 💻 Tecnologias Utilizadas

- Java
- Spring Boot
- MongoDB
- JWT (JSON Web Token) Authentication
- Docker
- Localstack
- AWS S3 Bucket

## ⚙ Requisitos

Antes de executar o projeto, certifique-se de ter os seguintes programas e tecnologias instalados em sua máquina:

- Java JDK 8 ou superior
- Maven
- Docker

## 🔧 Instalação

Para configurar e executar o projeto, siga as etapas abaixo:

1. Clone este repositório em sua máquina local:
```git clone https://github.com/bc-fullstack-03/schaidt-patriota-backend.git```

2. Acesse a pasta raiz do projeto:
```cd schaidt-patriota-backend```

3. Abra o arquivo `docker-compose.yaml` e verifique as configurações do Docker Compose.

4. Execute o seguinte comando para iniciar os containers Docker em modo detached:
```docker-compose up -d```

5. Acesse o terminal do container local:
```docker exec -it <nome_do_container> bash```

6. No terminal do container, defina as credenciais do usuário AWS executando o comando a seguir:
```aws configure --profile default```

Preencha as informações solicitadas: AWS Access Key ID, AWS Secret Access Key, Default region name e Default output format.

7. Crie um bucket S3 executando o comando abaixo:
```aws s3 mb s3://parrot-bucket --endpoint-url http://localhost:4566```

   Certifique-se de substituir `parrot-bucket` pelo nome desejado para o bucket.

8. Após configurar o bucket S3, você pode iniciar a aplicação.

## 📚 Utilização do Sistema

Após concluir as etapas de instalação, você estará pronto para utilizar o sistema. No entanto, lembre-se de que, caso pare e reinicie o container, será necessário criar novamente o bucket S3.

Para utilizar o sistema:

1. Verifique se os containers Docker estão em execução.
2. Acesse a documentação da API no [Swagger UI](http://localhost:8082/swagger-ui/index.html).
3. Explore os endpoints disponíveis para gerenciamento de usuários, publicações, comentários e interações.
4. Utilize as operações fornecidas pela API para interagir com o sistema de rede social. Isso inclui:

   - Cadastro, login e gerenciamento de usuários.
   - Criação, leitura, atualização e exclusão de publicações.
   - Curtir publicações.
   - Comentar em publicações.
   - Visualizar informações do perfil do usuário.
   - Buscar publicações por diferentes critérios, como por autor, data, hashtags, entre outras.

   Certifique-se de utilizar os dados retornados pela API para interagir com o sistema de rede social.

5. Caso encontre algum problema durante a utilização do sistema ou precise reiniciar os containers, é importante seguir novamente as etapas de configuração, especialmente a criação do bucket S3.

## 🌐 Documentação da API

A documentação completa da API pode ser acessada através do [Swagger UI](http://localhost:8082/swagger-ui/index.html) após iniciar os containers Docker e executar a aplicação.

Explore a documentação para obter informações detalhadas sobre os endpoints disponíveis, seus parâmetros e respostas.

## 🤝 Contribuição

Se você deseja contribuir para o Projeto Backend 🦜 Parrot, siga as etapas abaixo:

1. Faça um fork deste repositório.
2. Crie um branch para a sua feature ou correção: `git checkout -b minha-feature`.
3. Realize as modificações desejadas e faça commit das alterações: `git commit -m "Minha feature incrível"`.
4. Envie as alterações para o seu repositório remoto: `git push origin minha-feature`.
5. Abra um Pull Request neste repositório, descrevendo as modificações propostas.

Agradecemos desde já a sua contribuição!

## ☎️ Contato

Se tiver alguma dúvida, sugestão ou precisar de suporte relacionado ao Projeto Backend 🦜 Parrot, fique à vontade para entrar em contato com o desenvolvedor:

- LinkedIn: [Schaidt Patriota](https://www.linkedin.com/in/schaidt-patriota-ab8b13202/)

Será um prazer ajudar!
