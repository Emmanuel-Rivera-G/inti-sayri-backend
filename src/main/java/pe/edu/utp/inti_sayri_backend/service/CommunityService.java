package pe.edu.utp.inti_sayri_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.inti_sayri_backend.model.Community;
import pe.edu.utp.inti_sayri_backend.repository.CommunityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public Optional<Community> getCommunityById(Long id) {
        return communityRepository.findById(id);
    }

    public Community createCommunity(Community community) {
        return communityRepository.save(community);
    }

    public Community updateCommunity(Long id, Community community) {
        if (communityRepository.existsById(id)) {
            community.setId(id);
            return communityRepository.save(community);
        }
        return null;
    }

    public void deleteCommunity(Long id) {
        communityRepository.deleteById(id);
    }
}
