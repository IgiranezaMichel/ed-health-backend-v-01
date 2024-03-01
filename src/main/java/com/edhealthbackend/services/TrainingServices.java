package com.edhealthbackend.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.TrainingPage;
import com.edhealthbackend.repository.TrainingRepository;

@Service
public class TrainingServices {
    @Autowired
    private TrainingRepository trainingRepository;

    public Training saveOrUpdateTraining(Training training) {
        return trainingRepository.save(training);
    }

    public Training findTrainingById(long id) {
        return trainingRepository.findById(id).orElseThrow();
    }

    public String removeTrainingById(long id) {
        try {
            Training training = this.findTrainingById(id);
            if (training == null) {
                throw new Exception("Training not found");
            } else {
                trainingRepository.deleteById(id);
                return training.getTitle() + " deleted successful";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public long trainingSize() {
        return trainingRepository.count();
    }

    public TrainingPage trainingPage(int pageNumber, int pageSize, String sortBy) {
        Page<Training> trainingPage = trainingRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
        TrainingPage page = new TrainingPage(trainingPage.getContent(), trainingPage.getNumber(), trainingPage.getTotalPages(),
                this.trainingSize());
        return page;
    }

    public String deleteTrainingById(long id) {
        try {
            Training training = this.findTrainingById(id);
            trainingRepository.delete(training);
            return training.getTitle() + " removed successful";
        } catch (Exception e) {
            return "Training not found";
        }
    }

    public List<Training> getAllTraining(String sort) {
        return trainingRepository.findAll(Sort.by(sort));
    }

    public List<Training> searchTraining(String search) {
        return trainingRepository.findAll().stream()
                .filter(training -> (training.getDeadline().toString().toLowerCase().contains(search.toLowerCase())
                        || training.getDescription().toLowerCase().equals(search.toLowerCase())
                        || training.getNcnmApprovalStatus().toLowerCase().equals(search.toLowerCase())
                        || training.getTitle().toLowerCase().equals(search.toLowerCase())))
                .toList();
    }

    public ResponseEntity<String> updateTrainingStatus(long trainingId, String status) {
        Training training=this.findTrainingById(trainingId);
        if(training==null)return new ResponseEntity<>("Please specify correct training",HttpStatus.METHOD_NOT_ALLOWED);
        else {
            training.setNcnmApprovalStatus(status);
           training=this.saveOrUpdateTraining(training);
        }
       return new ResponseEntity<>(training.getTitle()+" Updated successful",HttpStatus.OK);
    }

    public TrainingPage findTrainingByNcnmApprovalStatusAndTrainingDeadlinetrainingPage(PaginationInput input,
            String status) {
              Page<Training>page=trainingRepository.findAllByNcnmApprovalStatusAndDeadlineBefore(status,LocalDateTime.now(),PageRequest.of(input.getPageNumber(),input.getPageSize(), Sort.by(input.getSort())));
        return  new TrainingPage(page.getContent(), page.getNumber(), page.getTotalPages(), this.trainingSize());
    }
}
