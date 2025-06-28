package com.raonhaje.memorymap.post.application;

import com.raonhaje.memorymap.common.exception.BusinessException;
import com.raonhaje.memorymap.common.exception.ErrorCode;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.member.repository.MemberJpaRepository;
import com.raonhaje.memorymap.post.domain.Post;
import com.raonhaje.memorymap.post.dto.PostCreateRequest;
import com.raonhaje.memorymap.post.dto.PostUpdateRequest;
import com.raonhaje.memorymap.post.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public Post createPost(Long kakaoId, PostCreateRequest request) {
        Member member = memberJpaRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        Post post = Post.create(request.title(), request.content());
        post.setMember(member);

        return postJpaRepository.save(post);
    }

    @Transactional
    public void updatePost(Long kakaoId, Long id, PostUpdateRequest request) {
        Member member = memberJpaRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        Post post = postJpaRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        if (!post.getMember().equals(member)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        post.updatePost(request.title(), request.content());
        postJpaRepository.save(post);
    }

    @Transactional
    public void deletePost(Long kakaoId, Long id) {
        Member member = memberJpaRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        Post post = postJpaRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        if (!post.getMember().equals(member)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        postJpaRepository.delete(post);
    }

    public Post getPost(Long kakaoId, Long id) {
        Member member = memberJpaRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        Post post = postJpaRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        if (!post.getMember().equals(member)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        return post;
    }

    public List<Post> getPostsByMember(Long kakaoId) {
        if (memberJpaRepository.findByKakaoId(kakaoId).isEmpty()) {
            throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
        }

        return postJpaRepository.findAllByMemberKakaoId(kakaoId);
    }
}
