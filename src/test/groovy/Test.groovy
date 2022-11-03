import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLEngine
import javax.net.ssl.TrustManager
import javax.net.ssl.X509ExtendedTrustManager
import java.security.cert.CertificateException
import java.security.cert.X509Certificate

class UnsafeTrustManager extends X509ExtendedTrustManager {

    @Override
    void checkClientTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {}

    @Override
    void checkServerTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {}

    @Override
    void checkClientTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {}

    @Override
    void checkServerTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {}

    @Override
    void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

    @Override
    void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

    @Override
    X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0] }

}

def sslContext = SSLContext.getInstance("TLS")
sslContext.init(null, new TrustManager[]{new UnsafeTrustManager()}, null)
def sslSocketFactory = sslContext.getSocketFactory()

def post = (HttpsURLConnection) new URL("https://localhost:10101?method=test_data_get_scoped_metrics&protocol_version=17").openConnection()
post.setSSLSocketFactory(sslSocketFactory)
post.connect()
print post.getResponseCode()

println post.getInputStream().getText()

post.disconnect()