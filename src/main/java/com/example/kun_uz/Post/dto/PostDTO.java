package com.example.kun_uz.Post.dto;

import com.example.kun_uz.Attach.dto.AttachDTO;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter

public class PostDTO {

    private Integer id;
    private String title;
    private String content;
    private List<AttachDTO> attachList;

}
