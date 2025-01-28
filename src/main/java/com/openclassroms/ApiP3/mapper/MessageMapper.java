package com.openclassroms.ApiP3.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openclassroms.ApiP3.dto.MessageDTO;
import com.openclassroms.ApiP3.model.AppUser;
import com.openclassroms.ApiP3.model.Message;
import com.openclassroms.ApiP3.model.Rental;
import com.openclassroms.ApiP3.repository.RentalRepository;
import com.openclassroms.ApiP3.repository.UserRepository;

@Component
public class MessageMapper {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RentalRepository rentalRepository;

  // Méthode pour transformer un MessageDTO en une entité Message
  public Message toEntity(MessageDTO messageDTO) {
    // Recherche de l'utilisateur à partir de son ID
    AppUser user = userRepository.findById(messageDTO.getUser_id())
            .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));

    // Recherche de la location à partir de son ID
    Rental rental = rentalRepository.findById(messageDTO.getRental_id())
            .orElseThrow(() -> new IllegalArgumentException("Location introuvable"));

    // Création d'une nouvelle instance de Message et attribution des valeurs
    Message message = new Message();
    message.setId(messageDTO.getId()); // On récupère l'ID du Message
    message.setUser(user); // Association de l'utilisateur à ce message
    message.setRental(rental); // Association de la location à ce message
    message.setMessage(messageDTO.getMessage()); // Le contenu du message

    return message;
  }

  // Méthode pour transformer une entité Message en MessageDTO
  public MessageDTO toDTO(Message message) {
    // Création d'un objet MessageDTO vide
    MessageDTO dto = new MessageDTO();
    dto.setId(message.getId()); // Récupération de l'ID du Message
    dto.setRental_id(message.getRental().getId()); // Récupération de l'ID de la location
    dto.setUser_id(message.getUser().getId()); // Récupération de l'ID de l'utilisateur
    dto.setMessage(message.getMessage()); // Le contenu du message

    return dto;
  }
}
