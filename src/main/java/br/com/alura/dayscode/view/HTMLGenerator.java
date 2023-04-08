package br.com.alura.dayscode.view;

import br.com.alura.dayscode.model.ImdbMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.List;

@Component
public class HTMLGenerator {

    @Autowired
    private PrintWriter writer;

    public void generate(List<ImdbMovie> movies) {
        writer.write("<!DOCTYPE html>");
        writer.write(head());
        writer.write("<body>");
        writer.write("<section class=\"container d-flex flex-column align-items-center\">");

        for (ImdbMovie movie : movies) {
            writer.write("""
                    <div class="card mb-3" style="max-width: 540px;">
                      <div class="row g-0">                    
                    """);
            writer.write("<div class=\"col-md-4\">");
            writer.write("<img src=\"" + movie.getImage() + "\"" + " class=\"img-fluid rounded-start\" />");
            writer.write("</div>");
            writer.write("""
                        <div class=\"col-md-8\">
                            <div class="card-body">
                        """);
            writer.write("<h5 class=\"card-title text-center fw-bold\">" + movie.getTitle() + "</h5>");
            writer.write("<div class=\"card-text d-flex justify-content-around\">");
            writer.write("<span class=\"badge text-bg-primary\"><small> Nota: </small>"+ movie.getImDbRating() +"</span>");
            writer.write("<span class=\"badge text-bg-primary\"><small> Ano: </small>"+ movie.getYear() +"</span>");
            writer.write("""   
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    """);
        }
        writer.write("</section>");
        writer.write("</body>");
        writer.write("</html>");
        writer.flush();
        writer.close();
    }

    private String head() {
        return """
                <head>
                    <meta name="author" content="Everton Rocha">
                    <meta name="keywords" content="java, springboot, rest, api">
                    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
                    <title>IMDB Movies</title>
                </head>                
                """;
    }
}
