# chamados-api

API REST para gerenciamento de chamados de assistência técnica, desenvolvida com Java e Spring Boot.

O projeto nasceu de um problema real: organizar os atendimentos da **VN Tech**, minha empresa de serviços de tecnologia. Os status do chamado seguem o fluxo verdadeiro de um reparo, do recebimento do equipamento até a entrega.

> 🚧 **Projeto em desenvolvimento.** Ele evolui conforme avanço nos estudos de Spring. Veja os [próximos passos](#próximos-passos).

## Tecnologias

- Java 25
- Spring Boot 4 (Spring Web MVC)
- Maven
- Jackson (serialização JSON)

## Arquitetura

A aplicação é organizada em camadas, cada uma com uma única responsabilidade:

```
Controller  →  Service  →  (Repository)
   HTTP        regras de     dados
               negócio
```

- **Controller:** recebe as requisições HTTP e devolve as respostas. Não contém regra de negócio.
- **Service:** concentra a lógica e converte as entidades em DTOs.
- **DTO:** define exatamente o que a API expõe, sem revelar a entidade interna.

```
src/main/java/com/vinicius/chamados_api
├── controller   # endpoints REST
├── service      # regras de negócio e conversão para DTO
├── dto          # objetos de resposta (records)
└── entity       # entidades do domínio
    └── enums    # status do chamado
```

## Status do chamado

| Status | Significado |
| --- | --- |
| `RECEBIDO` | Equipamento entregue pelo cliente |
| `EM_AVALIACAO` | Diagnóstico em andamento |
| `AGUARDANDO_APROVACAO` | Orçamento enviado, aguardando o cliente |
| `EM_REPARO` | Serviço em execução |
| `FINALIZADO` | Serviço concluído |
| `CANCELADO` | Orçamento recusado ou atendimento encerrado |

## Endpoints

| Método | Rota | Descrição |
| --- | --- | --- |
| GET | `/chamados` | Lista todos os chamados |
| GET | `/chamados/{id}` | Busca um chamado pelo id |

Exemplo de resposta de `GET /chamados/2`:

```json
{
  "id": 2,
  "titulo": "Preventiva notebook",
  "status": "AGUARDANDO_APROVACAO"
}
```

## Como executar

Pré-requisito: JDK 25 instalado.

```bash
git clone https://github.com/Vinicius-code22/chamados-api.git
cd chamados-api

# Linux / macOS / Git Bash
./mvnw spring-boot:run

# Windows (PowerShell / CMD)
mvnw.cmd spring-boot:run
```

A API sobe em `http://localhost:8080`.

## Próximos passos

- [ ] Criação de chamados (`POST`) com DTO de entrada
- [ ] Atualização e remoção (`PUT` e `DELETE`)
- [ ] Persistência em banco de dados com Spring Data JPA
- [ ] Validação de dados com Bean Validation
- [ ] Tratamento global de erros (ex.: `404` para chamado inexistente)
- [ ] Autenticação com Spring Security

## Autor

**Vinicius Rodrigues Viana**
[LinkedIn](https://www.linkedin.com/in/viniciusvianaa) · [GitHub](https://github.com/Vinicius-code22) · [Portfólio](https://viniciusviana.website)
