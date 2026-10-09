package main.java.com.jgunzalesindustries.control.recursos.humanos.service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.request.ApplicantRequest;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response.ApplicantResponse;
import main.java.com.jgunzalesindustries.control.recursos.humanos.model.Applicant;
import main.java.com.jgunzalesindustries.control.recursos.humanos.repository.ApplicantRepository;


public class ApplicantService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    private final ApplicantRepository applicantRepository;

    public ApplicantService() {
        this.applicantRepository = new ApplicantRepository();
    }

    public ApplicantResponse create(ApplicantRequest request) {
        validate(request);
        
        if (applicantRepository.findById(request.getAdressId()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente registrado con esa direccion.");
        }

        // El service genera lo que el usuario no escribe
        Applicant applicant = new Applicant(
                UUID.randomUUID().toString(),
                request.getNamesApplicant(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getAdressId(),
                request.getAge());

        applicantRepository.save(applicant);
        return toResponse(applicant);
    }   

    public ApplicantResponse update(String id, ApplicantRequest request) {
        validate(request);

        Applicant existing = applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aplicante no encontrado."));

        applicantRepository.findById(request.getAdressId()).ifPresent(other -> {
            if (!other.getAddresId().equals(id)) {
                throw new IllegalArgumentException("Ya existe otro cliente registrado con ese DPI.");
            }
        });

        existing.setAddresId(request.getAdressId());
        existing.setNamesApplicant(request.getNamesApplicant());
        existing.setLastName(request.getLastName());
        existing.setPhone(request.getPhone());
        existing.setEmail(request.getEmail());
        existing.setAge(request.getAge());

        applicantRepository.update(existing);
        return toResponse(existing);
    }

    public void delete(String id) {
        applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aplicante no encontrado."));
        applicantRepository.delete(id);
    }

    public List<ApplicantResponse> list() {
        return applicantRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private void validate(ApplicantRequest r) {
        if (r.getAdressId() == null) {
            throw new IllegalArgumentException("la direccion debe tener digitos");
        }
        if (r.getNamesApplicant() == null || r.getNamesApplicant().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (r.getLastName() == null || r.getLastName().isBlank()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        if (r.getEmail() != null && !r.getEmail().isBlank()
                && !EMAIL_PATTERN.matcher(r.getEmail()).matches()) {
            throw new IllegalArgumentException("El correo no tiene un formato válido.");
        }
    }

    private ApplicantResponse toResponse(Applicant a) {
        return new ApplicantResponse(
                a.getApplicant(), a.getNamesApplicant(), a.getLastName(),
                a.getPhone(), a.getEmail(), a.getAddresId(), a.getAge());
    }
}
