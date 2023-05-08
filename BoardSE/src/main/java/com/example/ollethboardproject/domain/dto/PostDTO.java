<<<<<<< HEAD
package com.example.ollethboardproject.domain.dto;

import com.example.ollethboardproject.domain.entity.Post;
import com.example.ollethboardproject.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private Member member;
    private int ollehCount;

    public static PostDTO fromEntity(Post post) {
        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getMember(), post.getOllehsList().size());
    }

}
=======
package com.example.ollethboardproject.domain.dto;

import com.example.ollethboardproject.domain.entity.Post;
import com.example.ollethboardproject.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private Member member;
    private int ollehCount;

    public static PostDTO fromEntity(Post post) {
        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getMember(), post.getOllehsList().size());
    }
}
>>>>>>> main
