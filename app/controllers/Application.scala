package controllers

import javax.inject.Inject

import play.api._
import play.api.mvc._
import play.api.i18n._
import play.api.libs.concurrent.Execution.Implicits._

import slick.driver.JdbcProfile

import models.Article
import dao.ArticleDAO

class Application @Inject() (articleDao: ArticleDAO, val messagesApi: MessagesApi) extends Controller with I18nSupport {
	
	def index = Action.async {
		articleDao.all().map{case (articles) => Ok(views.html.index(articles.length.toString)) }
		//Ok(views.html.index("Your new application is ready."))
	}
}