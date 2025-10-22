import javax.management.loading.ClassLoaderRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifsp.projetoOrnato.cliente;

public class ClienteController {

    @Controller
    @RequestMapping ("/login")
    public class UsuarioController {
        
        private final ClassLoaderRepository clienteRepo;

        public UsuarioController (ClassLoaderRepository clienteRepo) {
            this.clienteRepo = clienteRepo;
        }
        @GetMapping
         public String login (){
            return "login";
         }

         @PostMapping
         public String autenticar (@RequestParam String email, @RequestParam String senha){

            cliente cliente = ((Object) clienteRepo).findByEmail(email);
           
            if (cliente != null && cliente.getSenha().equals(senha)) {
            return "redirect:/";
          }
          return "redirect:/login?erro";
         }
    }
    
}
