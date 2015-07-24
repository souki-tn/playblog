package controllers

import javax.inject.Inject
import play.api._
import play.api.mvc._
import play.api.i18n._

class Application @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {
	def index = Action {
		Ok(views.html.index("Your new application is ready.")(messagesApi.preferred(Seq(new Lang("fr")))))
	}
}