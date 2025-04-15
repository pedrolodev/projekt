package dev.jesuspedro.demo.security.users.infraestructure.exceptions;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
public abstract class Response {
    private final HttpStatus status;
    private final LocalDateTime time;

    public Response(HttpStatus status){
        this.status = status;
        this.time = this.generateTime();
    }

    private LocalDateTime generateTime(){
        return LocalDateTime.now();
    }
}
