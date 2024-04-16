package br.com.dogmatch.apiprincipal.Service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import br.com.dogmatch.apiprincipal.infra.Exception.StorageException;

@Service
public class ArmazenarFotoService {
	
	public String armzenar(MultipartFile file, String nomeArquivo) {
		
		String projectId = "dog-match-420420";
		String bucketName = "fotos_pet";
		
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		Bucket bucket = storage.get(bucketName);
				
		String blobName =  nomeArquivo + ".png";
		
		Blob blob;
		try {
			blob = bucket.create(blobName, file.getBytes());
			return blob.getMediaLink();
		} catch (IOException e) {
			throw new StorageException("Erro ao armazenar imagem");
		}
	}
}
