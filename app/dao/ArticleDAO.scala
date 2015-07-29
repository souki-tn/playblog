package dao

import scala.concurrent.Future

import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

import models.Article

class ArticleDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
	import driver.api._
	
	private val articles = TableQuery[ArticleTable]
	def all() = db.run(articles.result)
	def insert(article: Article) = db.run(articles += article)
	
	class ArticleTable(tag: Tag) extends Table[Article](tag, "articles") {
		def id = column[Long]("id", O.PrimaryKey)
		def title = column[String]("title")
		def body = column[String]("body")
		def * = (id, title, body) <> (Article.tupled, Article.unapply)
	}
}
