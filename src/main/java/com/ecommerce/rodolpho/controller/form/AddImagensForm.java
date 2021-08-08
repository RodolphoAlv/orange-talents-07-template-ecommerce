package com.ecommerce.rodolpho.controller.form;

import com.ecommerce.rodolpho.model.Imagem;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AddImagensForm {
    @NotNull
    @Size(min = 1, message = "Deve ter uma imagem ou mais!")
    private List<MultipartFile> imagens;

    public AddImagensForm() {}

    public List<MultipartFile> getImagens() {
        return imagens;
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<String> enviarImagens() {

        List<String> links = new ArrayList<>();
        if(this.imagens != null)
            imagens.forEach(imagem -> links.add("www.cloud.com/"
                        + Objects.requireNonNull(imagem.getOriginalFilename())
                                .replace(" ", "_"))
            );

        return links;
    }
}
