#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

#if($openapi_app_async_library == "mutiny")
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.smallrye.mutiny.Uni;
#end
#if($openapi_app_async_library == "rxjava2")
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.core.Promise;
#end
#if($openapi_app_async_library == "vertx")
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
#end

/**
 * Main Vert.x Verticle, entrypoint for this application
 */
public class MainVerticle extends AbstractVerticle {

	@Override
#if($openapi_app_async_library == "mutiny")
	public Uni<Void> asyncStart() throws Exception {
		return Uni.createFrom().nothing();
#else
	public void start (Promise < Void > startPromise) throws Exception {
	  startPromise.complete();
#end
	}
}
