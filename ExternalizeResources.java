// ## Externalize BOOK Resources
/*
REST API normally operates with the exchange of resources that are externalized through a particular data notation. 
I make use of the JSON (JavaScript Object Notation) since the client-side front-end technologies are mainly JavaScript-based, 
which makes it easier to create and parse data in the JSON format.

Before implementing the endpoints identified in the previous step, I provide externalizations of 
the application resources using an interchangeble notation like JSON. 
There are some libraries that facilitate that externalization through the implementation of three interfaces:

JsonViewer - Given a particular domain object, creates a JsonElement that represents that domain object.
JsonCreator - Given a particular JSON object, creates a new domain object.
JsonUpdater - Given a particular JSON object, updates an existing domain object.
JsonAdapter - This interface implements the above three interfaces.
Here is a case of the BookAdapter (DB Book resource (book: id, title, author)) that implements the JsonAdapter interface, 
i.e. all three JsonViewer, JsonCreator and JsonUpdater interfaces:
*/

// The annotation @DefaultJsonAdapter(Book.class) registers this Adapter class in the the JSON library, 
// so that it knows what adapter to use when handling a object of the Book class.
@DefaultJsonAdapter(Book.class)
public class BookAdapter implements JsonAdapter<Book> {

  @Override
  public Book create(JsonElement jsonElement, JsonBuilder ctx) {
    final JsonObject json = jsonElement.asJsonObject();
    String title = json.get("title");
    String author = json.get("author");
    return BookManager.createBook(title, author);
  }


  @Override
  public Book update(JsonElement jsonElement, Book book, JsonBuilder ctx) {
    final JsonObject json = jsonElement.asJsonObject();
    String title = json.get("title");
    String author = json.get("author");
    return book.update(title, author);
  }

	
  @Override
  public JsonElement view(Book book, JsonBuilder ctx) {
    final JsonObject json = new JsonObject();
    json.addProperty("id", book.getExternalId());
    json.addProperty("title", book.getTitle());
    json.addProperty("author", book.getAuthor());
    return json;
  }
}


