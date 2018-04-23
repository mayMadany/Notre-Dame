/*
* Copyright (C) 2013 The Android Open Source Project
* Modifications copyright (C) 2018 Thanh-Son-Philippe Lam
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package ca.etsmtl.etsmobile.data.repository.login

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.spec.AlgorithmParameterSpec
import java.util.Calendar
import java.util.Date
import javax.security.auth.x500.X500Principal

/**
 * Created by Sonphil on 20-04-18.
 */

class KeyStoreUtils constructor(
    private val context: Context
) {
    companion object {
        const val KEYSTORE_PROVIDER_ANDROID_KEYSTORE = "AndroidKeyStore"
    }

    private val keyStore: KeyStore = getAndroidKeyStore()

    /**
     * Creates KeyStore instance. The KeyStore's purpose is to store cryptographic keys.
     */
    private fun getAndroidKeyStore(): KeyStore {
        val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
        keyStore.load(null)

        return keyStore
    }

    /**
     * Creates asymmetric cryptographic keys (public key and private key)
     *
     * @param alias The created key pair's identifier. If the alias already exists, it will be
     * overwritten with new key pair
     * @return Asymmetric cryptographic key pair (public key and private key)
     */
    fun createAndroidKeyStoreAsymmetricKey(alias: String): KeyPair {
        val generator: KeyPairGenerator = KeyPairGenerator.getInstance("RSA", KEYSTORE_PROVIDER_ANDROID_KEYSTORE)

        initGenerator(generator, alias)

        // Generate and return key pair
        return generator.generateKeyPair()
    }

    /**
     * Initializes the provided KeyPairGenerator with specification
     *
     * @param generator The KeyPairGenerator instance
     * @param alias The identifier of the key pair that would be generated by using the provided
     * generator
     */
    private fun initGenerator(generator: KeyPairGenerator, alias: String) {
        val spec: AlgorithmParameterSpec = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getKeyGenParameterSpec(alias)
        } else {
            val startTime = Calendar.getInstance().time
            val endDate = Calendar.getInstance()
            endDate.add(Calendar.YEAR, 20)
            val endTime = endDate.time

            getKeyPairGeneratorSpec(alias, startTime, endTime)
        }

        generator.initialize(spec)
    }

    /**
     * Returns the required parameters needed to initialize the KeyPairGenerator
     *
     * The parameters match [CipherUtils.TRANSFORMATION]
     *
     * @param alias The identifier of the key pair that would be generated by using the provided
     * generator
     * @return The required parameters needed to initialize the KeyPairGenerator
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun getKeyGenParameterSpec(alias: String): AlgorithmParameterSpec {
        // Make sure that the parameters match [CipherUtils.TRANSFORMATION]
        val builder = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)

        return builder.build()
    }

    /**
     * Returns the required parameters needed to initialize the KeyPairGenerator
     *
     * @param alias The identifier of the key pair that would be generated by using the provided
     * generator
     * @param start The start date of validity for the pair that would be generated
     * @param end The end date of validity for the pair that would be generated
     * @return The required parameters needed to initialize the KeyPairGenerator
     */
    @Suppress("DEPRECATION")
    private fun getKeyPairGeneratorSpec(alias: String, start: Date, end: Date): AlgorithmParameterSpec {
        val builder = KeyPairGeneratorSpec.Builder(context)
                // You'll use the alias later to retrieve the key.  It's a key for the key!
                .setAlias(alias)
                // The subject used for the self-signed certificate of the generated pair
                .setSubject(X500Principal("CN=$alias"))
                // The serial number used for the self-signed certificate of the
                // generated pair.
                .setSerialNumber(BigInteger.valueOf(1337))
                // Date range of validity for the generated pair.
                .setStartDate(start)
                .setEndDate(end)

        return builder.build()
    }

    /**
     * Returns the key pair (public key and private key) corresponding to the provided alias
     *
     * @param alias The key's pair identifier
     * @return The key pair corresponding to the provided alias
     */
    fun getAndroidKeyStoreAsymmetricKeyPair(alias: String): KeyPair? {
        val privateKey = keyStore.getKey(alias, null) as PrivateKey?
        val publicKey = keyStore.getCertificate(alias)?.publicKey

        return if (privateKey != null && publicKey != null) {
            KeyPair(publicKey, privateKey)
        } else {
            null
        }
    }

    fun deleteAndroidKeyStoreKeyEntry(alias: String) = keyStore.deleteEntry(alias)
}