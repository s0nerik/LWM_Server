import com.fasterxml.jackson.databind.JsonNode;
import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.BaseOAuth20Client;
import org.pac4j.oauth.client.exception.OAuthCredentialsException;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.OAuthAttributesDefinitions;
import org.pac4j.oauth.profile.google2.Google2Profile;
import org.scribe.builder.api.GoogleApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.oauth.ProxyOAuth20ServiceImpl;

public class Google2ClientREST extends BaseOAuth20Client<Google2Profile> {

    public Google2ClientREST() {
    }

    public Google2ClientREST(final String key, final String secret) {
        setKey(key);
        setSecret(secret);
    }

    @Override
    protected Google2ClientREST newClient() {
        return new Google2ClientREST();
    }

    @Override
    protected void internalInit() {
//        super.internalInit();
        this.service = new ProxyOAuth20ServiceImpl(new GoogleApi20(), new OAuthConfig(this.key, this.secret,
                "postmessage",
                SignatureType.Header,
                "", null),
                this.connectTimeout, this.readTimeout, this.proxyHost,
                this.proxyPort, false, true);
    }

    @Override
    protected String getProfileUrl(final Token accessToken) {
        return "https://www.googleapis.com/oauth2/v2/userinfo";
    }

    @Override
    protected Google2Profile extractUserProfile(final String body) {
        final Google2Profile profile = new Google2Profile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
            for (final String attribute : OAuthAttributesDefinitions.google2Definition.getPrincipalAttributes()) {
                profile.addAttribute(attribute, JsonHelper.get(json, attribute));
            }
        }
        return profile;
    }

    @Override
    protected boolean requiresStateParameter() {
        return false;
    }

    @Override
    protected boolean hasBeenCancelled(final WebContext context) {
        final String error = context.getRequestParameter(OAuthCredentialsException.ERROR);
        // user has denied permissions
        if ("access_denied".equals(error)) {
            return true;
        } else {
            return false;
        }
    }
}
