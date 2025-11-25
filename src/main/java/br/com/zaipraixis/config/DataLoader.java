package br.com.zaipraixis.config;

import br.com.zaipraixis.domain.Consultor;
import br.com.zaipraixis.repositories.ConsultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
public class DataLoader implements CommandLineRunner {


    @Autowired
    private ConsultorRepository consultorRepository;

    @Profile("dev")
    @Override
    public void run(String... args) throws Exception {


        if (consultorRepository.count() > 0) {
            System.out.println("[seed:dev] Dados já existentes — pulando carga inicial.");
            return;
        }

//        System.out.println("[CLEANUP] limpando o documento");
//        consultorRepository.deleteAll();

        Consultor consul1 = new Consultor("Denis Machado",
                "(11) 12345-5654", "denis@email.com", "TI",
                LocalDate.now());

        consultorRepository.save(consul1);
        System.out.println("[DATALOADER] Cadastro feito");


    }
}
