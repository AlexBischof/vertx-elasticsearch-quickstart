import io.gatling.core.Predef._ // 2
import io.gatling.http.Predef._
import scala.concurrent.duration._

class CustomerResourceTest extends Simulation {

  val conf = http.baseURL("http://localhost:8080")

  val scn = scenario("Gatling")
    .during(1 minute) {
      exec(
        http("json").get("/products")
      )
    }

  setUp(scn.inject(rampUsers(5) over (30 seconds)))
    .protocols(conf)

}