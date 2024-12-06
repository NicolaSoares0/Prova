import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class AlunoHttpServer {

    private static final Map<Integer, Aluno> alunos = new HashMap<>();
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/aluno");
        server.setExecutor(null);
        server.start();

        System.out.println("Servidor HTTP iniciado na porta 8000");
    }

    static class Aluno {
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

        public Aluno getAlunoById(int id) {
            lock.lock();
            try {
                return alunos.get(id);
            } finally {
                lock.unlock();
            }
        }

        public boolean deleteAluno(int id) {
            lock.lock();
            try {
                return alunos.remove(id) != null;
            } finally {
                lock.unlock();
            }
        }

        public Aluno createAluno() {
            lock.lock();
            try {
                Random random = new Random();
                int id;
                do {
                    id = random.nextInt(1000) + 1; // Gera um ID aleatório entre 1 e 1000
                } while (alunos.containsKey(id)); // Garante que o ID não seja repetido

                String nome = "Aluno" + id;
                Aluno aluno = new Aluno(id, nome);
                alunos.put(id, aluno);
                return aluno;
            } finally {
                lock.unlock();
            }
        }
    }

