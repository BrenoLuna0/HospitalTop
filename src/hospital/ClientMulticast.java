package hospital;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class ClientMulticast extends Thread{

    private int porta;
    private InetAddress ipGrupo = null;
    private MulticastSocket multicastSocket = null;

    public ClientMulticast(String ip, String porta){
        setIpPortaGrupo(ip, porta);
    }

    @Override
    public void run() {
        entrarNoGrupoMulticast();
        monitorarRequests();
    }

    /**
     * Entra no grupo multicast e responde caso possa atender a requisição
     */
    private void monitorarRequests() {

        /*Aguardar requests*/
        System.out.println("Esperando requisições multicast...");
        while (multicastSocket != null){
            DatagramPacket pct_recebido = receberResposta();

            /*Cria uma thread para tratar cada conexão*/
            ThreadConn conn = new ThreadConn(multicastSocket, pct_recebido);
            conn.start();
        }

    }

    /**
     * Aguarda para receber uma resposta do grupo
     *
     * @return DatagramPacket com a resposta recebida
     */
    public DatagramPacket receberResposta(){
        byte[] buffer = new byte[1024];
        DatagramPacket recebido = new DatagramPacket(buffer, buffer.length);
        try {
            multicastSocket.receive(recebido);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return recebido;
    }

    /**
     * Finaliza o servidor multicast atual
     */
    public void matarServerMulticast(){
        /*Sair do grupo e fechar socket*/
        this.abandonarGrupo();
        this.fecharConexao();
        multicastSocket = null;
    }

    /**
     * Sai do grupo multicast atual
     */
    private void abandonarGrupo(){
        try {
            this.multicastSocket.leaveGroup(this.ipGrupo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fecha o socket da conexão
     */
    private void fecharConexao(){
        if (this.multicastSocket != null){
            this.multicastSocket.close();
        }
    }

    public int getPorta() {
        return porta;
    }

    public InetAddress getIpGrupo() {
        return ipGrupo;
    }

    /**
     * Define o IP e a porta do servidor
     */
    private void setIpPortaGrupo(String ip, String porta) {
        try {
            this.ipGrupo = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        this.porta = Integer.parseInt(porta);
    }

    /**
     * Entra no grupo multicast com o ipGrupo e porta informados
     */
    private void entrarNoGrupoMulticast() {
        try {
            this.multicastSocket = new MulticastSocket(this.porta);
            this.multicastSocket.joinGroup(this.ipGrupo);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Envia uma mensagem para o grupo
     * @param msg mensagem a ser enviada
     */
    public void enviarMsg(String msg) {

        DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.length(), this.ipGrupo, this.porta);

        try {
            this.multicastSocket.send(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
