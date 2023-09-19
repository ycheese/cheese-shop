import ujson.Value
import zio.json.{DecoderOps, DeriveJsonCodec, JsonCodec}

case class Reservation(id: Int,
                       user: User,
                       date: String,
                       time: String,
                       guests: String,
                       isClosed: Boolean = false,
                       isPaied: Boolean = false) {
  def close() = Reservation(this.id, this.user, this.date, this.time, this.guests, true, this.isPaied)
}

object Reservation {
  implicit val codec: JsonCodec[Reservation] = DeriveJsonCodec.gen[Reservation]

  def parseJson(value: Value): Either[String, List[Reservation]] = for {
    json <- value.toString().fromJson[List[Reservation]]
  } yield json
}
