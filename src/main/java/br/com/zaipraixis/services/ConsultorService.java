package br.com.zaipraixis.services;

import br.com.zaipraixis.domain.Consultor;
import br.com.zaipraixis.dtos.ConsultorDTO;
import br.com.zaipraixis.repositories.ConsultorRepository;
import br.com.zaipraixis.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultorService {

    @Autowired
    private ConsultorRepository consultorRepository;


    public List<ConsultorDTO> findAll() {
        return consultorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public ConsultorDTO findById(String id) {
        Consultor consultor = consultorRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Consultor não encontrado! Id: " + id)
                );
        return toDTO(consultor);
    }


    public ConsultorDTO create(ConsultorDTO dto) {
        Consultor consultor = toEntity(dto);
        consultor.setId(null); // garante que o Mongo vai gerar um ObjectId novo
        consultor.setDataCriacao(LocalDate.now());
        Consultor saved = consultorRepository.save(consultor);
        return toDTO(saved);
    }


    public ConsultorDTO update(String id, ConsultorDTO dto) {
        Consultor existing = consultorRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Consultor não encontrado! Id: " + id)
                );

        existing.setNome(dto.getNome());
        existing.setTelefone(dto.getTelefone());
        existing.setEmail(dto.getEmail());
        existing.setAreaEspecializacao(dto.getAreaEspecializacao());

        Consultor updated = consultorRepository.save(existing);
        return toDTO(updated);
    }


    public void delete(String id) {
        if (!consultorRepository.existsById(id)) {
            throw new ObjectNotFoundException("Consultor não encontrado! Id: " + id);
        }
        consultorRepository.deleteById(id);
    }


    private ConsultorDTO toDTO(Consultor entity) {
        return new ConsultorDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getEmail(),
                entity.getAreaEspecializacao(),
                entity.getDataCriacao()
        );
    }

    private Consultor toEntity(ConsultorDTO dto) {
        return new Consultor(
                dto.getNome(),
                dto.getTelefone(),
                dto.getEmail(),
                dto.getAreaEspecializacao(),
                dto.getDataCriacao()
        );
    }
}
