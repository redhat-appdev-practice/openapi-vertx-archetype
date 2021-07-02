#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 * Main Vert.x Verticle, entrypoint for this application
 */
public class MainVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		startPromise.complete();
	}
}
