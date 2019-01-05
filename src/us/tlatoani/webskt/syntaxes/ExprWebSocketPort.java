package us.tlatoani.webskt.syntaxes;

import us.tlatoani.mundocore.property_expression.MundoPropertyExpression;
import org.java_websocket.WebSocket;

import java.net.InetSocketAddress;
import java.util.Optional;

/**
 * Created by Tlatoani on 9/4/17.
 */
public class ExprWebSocketPort extends MundoPropertyExpression<WebSocket, Number> {

    private InetSocketAddress getSocketAddress(WebSocket webSocket) {
        switch (getPropertyName()) {
            case "local port":
                return webSocket.getLocalSocketAddress();
            case "remote port":
            case "external port":
                return webSocket.getRemoteSocketAddress();
        }
        throw new IllegalStateException("Illegal getPropertyName() value: " + getPropertyName());
    }

    @Override
    public Number convert(WebSocket webSocket) {
        return Optional.ofNullable(getSocketAddress(webSocket)).map(InetSocketAddress::getPort).orElse(null);
    }
}