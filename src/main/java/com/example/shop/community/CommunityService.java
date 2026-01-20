package com.example.shop.community;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    @Transactional
    public Long savePost(Community post) {
        return communityRepository.save(post).getId();
    }

    @Transactional(readOnly = true)
    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        communityRepository.deleteById(id);
    }
}