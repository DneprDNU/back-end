package org.dnu.filestorage.security.transfer;

/**
 * @author demyura
 * @since 19.11.14
 */
public class TokenTransfer {

    private final String token;


    public TokenTransfer(String token) {
        this.token = token;
    }


    public String getToken() {
        return this.token;
    }

}
