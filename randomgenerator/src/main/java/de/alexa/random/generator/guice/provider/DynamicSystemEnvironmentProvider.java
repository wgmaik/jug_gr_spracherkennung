package de.alexa.random.generator.guice.provider;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.util.Base64;
import com.google.common.base.Charsets;
import com.google.inject.Provider;

import java.nio.ByteBuffer;

public class DynamicSystemEnvironmentProvider implements Provider<String> {
	private final String keyName;
	private final boolean decrypt;

	public DynamicSystemEnvironmentProvider(String keyName, boolean decrypt) {
		this.keyName = keyName;
		this.decrypt = decrypt;
	}

	@Override
	public String get() {
		if (decrypt) {
			return decrypt(keyName);
		} else {
			return System.getenv(keyName);
		}
	}
	
	private String decrypt(String key) {
        byte[] encryptedKey = Base64.decode(System.getenv(key));
        AWSKMS client = AWSKMSClientBuilder.defaultClient();

        DecryptRequest request = new DecryptRequest().withCiphertextBlob(ByteBuffer.wrap(encryptedKey));
        ByteBuffer plainTextKey = client.decrypt(request).getPlaintext();
        return new String(plainTextKey.array(), Charsets.UTF_8);
    }
}
