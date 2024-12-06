import java.io.IOException;

import javax.xml.ws.spi.http.HttpExchange;

public class Aluno {
    int id;
    String nome;

    Aluno(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    String toHtml() {
        return "<html><body><h1>Aluno ID: " + id + "</h1><p>Nome: " + nome + "</p></body></html>";
    }
}
public class AlunoHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String response = "";
        int statusCode = 200;

        if (requestMethod.equals("GET")) {
            if (path.startsWith("/aluno/")) {
                int alunoId = Integer.parseInt(path.split("/")[2]);
                Aluno aluno = getAlunoById(alunoId);
                                if (aluno != null) {
                                    response = aluno.toHtml();
                                } else {
                                    statusCode = 404;
                                    response = "<html><body><h1>Aluno não encontrado</h1></body></html>";
                                }
                            }
                        } else if (requestMethod.equals("DELETE")) {
                            if (path.startsWith("/aluno/")) {
                                int alunoId = Integer.parseInt(path.split("/")[2]);
                                boolean sucesso = deleteAluno(alunoId);
                                                                if (sucesso) {
                                                                    response = "<html><body><h1>Aluno excluído com sucesso</h1></body></html>";
                                                                } else {
                                                                    statusCode = 404;
                                                                    response = "<html><body><h1>Aluno não encontrado</h1></body></html>";
                                                                }
                                                            }
                                                        } else if (requestMethod.equals("POST")) {
                                                            Aluno novoAluno = createAluno();
                                                                                                                        response = "<html><body><h1>Aluno criado com sucesso</h1><p>ID: " + novoAluno.id + " Nome: " + novoAluno.nome + "</p></body></html>";
                                                                                                                    }
                                                                                                            
                                                                                                                    ((Object) exchange).sendResponseHeaders(statusCode, response.getBytes().length);
                                                                                                                    try (OutputStream os = exchange.getResponseBody()) {
                                                                                                                        os.write(response.getBytes());
                                                                                                                    }
                                                                                                                }
                                                                                                            
                                                                                                                private Aluno createAluno() {
                                                                    // TODO Auto-generated method stub
                                                                    throw new UnsupportedOperationException("Unimplemented method 'createAluno'");
                                                                }
                                                            
                                                                                                                private boolean deleteAluno(int alunoId) {
                                        // TODO Auto-generated method stub
                                        throw new UnsupportedOperationException("Unimplemented method 'deleteAluno'");
                                    }
                                
                                                    private Aluno getAlunoById(int alunoId) {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException("Unimplemented method 'getAlunoById'");
                    }
    
}
