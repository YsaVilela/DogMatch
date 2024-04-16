package br.com.dogmatch.apiprincipal.dev;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoConverter {
	
    public MultipartFile convertFileToMultipartFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream input = new FileInputStream(file);

        // Obter o tamanho do arquivo
        long fileSize = file.length();

        // Criar um array de bytes do tamanho do arquivo
        byte[] bytes = new byte[(int) fileSize];

        // Lê todos os bytes do arquivo para o array de bytes
        int bytesRead = input.read(bytes);

        // Fechar o FileInputStream
        input.close();

        if (bytesRead != fileSize) {
            throw new IOException("Não foi possível ler o arquivo completamente: " + filePath);
        }

        // Criar um objeto MultipartFile a partir do array de bytes
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return file.getName();
            }

            @Override
            public String getOriginalFilename() {
                return file.getName();
            }

            @Override
            public String getContentType() {
                // Defina o tipo de conteúdo da imagem corretamente, por exemplo: "image/jpeg"
                return "image/jpeg";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return fileSize;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return bytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(bytes);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                // Escrever os bytes do arquivo no destino especificado usando FileUtils.writeByteArrayToFile
                FileUtils.writeByteArrayToFile(dest, bytes);
            }
        };

        return multipartFile;
    }

    public MultipartFile converter() {
        PhotoConverter converter = new PhotoConverter();
        String filePath = "C:\\dev\\projects\\DogMatch\\Back-End\\API\\Chaves\\Zac.jpeg";

        try {
            MultipartFile multipartFile = converter.convertFileToMultipartFile(filePath);
            return multipartFile;

        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
}
