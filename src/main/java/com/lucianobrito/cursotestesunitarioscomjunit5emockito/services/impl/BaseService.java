package com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.impl;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.BaseEntity;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.dto.BaseDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Classe genérica para transformação de Entidades em DTOs e vice-versa
 *
 * @author Luciano Brito
 * @see <a href="https://github.com/lucianobritodev">github.com/lucianobritodev</a>
 */
@Slf4j
public abstract class BaseService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    protected MessageSource messageSource;

    /**
     * Método para conversão de Entidade em DTO
     * @param <Entity> Classe Entity que será utilizada para obter os dados que serão mapeados.
     * @param <Dto>    Classe DTO alvo que receberá os dados do mapeamento.
     * @author Luciano Brito
     * @see <a href="https://github.com/lucianobritodev">github.com/lucianobritodev</a>
     */
    protected <Entity extends BaseEntity, Dto extends BaseDto> Dto entityToDto(final Entity entity, final Class<Dto> dto) {
        Dto result = null;
        try {
            result = modelMapper.map(entity, dto);
        } catch (Exception e) {
            log.error("{}", this.getMessage("EntityToDtoException", entity.getClass().getSimpleName()));
        }
        return result;
    }

    /**
     * Método para conversão de DTO em Entitade
     * @param <Dto>    Classe DTO que será utilizada para obter os dados que serão mapeados.
     * @param <Entity> Classe Entity alvo que receberá os dados do mapeamento.
     * @author Luciano Brito
     * @see <a href="https://github.com/lucianobritodev">github.com/lucianobritodev</a>
     */
    protected <Dto extends BaseDto, Entity extends BaseEntity> Entity dtoToEntity(final Dto dto, final Class<Entity> entity) {
        Entity result = null;
        try {
            result = modelMapper.map(dto, entity);
        } catch (Exception e) {
            log.error("{}", this.getMessage("DtoToEntityException", dto.getClass().getSimpleName()));
        }
        return result;
    }

    /**
     * @param keyMessageTemplate template de mensagem que será utilizado para interpolar com conteúdo do arquivo
     *                           message.properties e obter o seu valor.
     * @param parameterMessage   parâmetros que serão utilizados para interpolar com as mensagens do arquivo
     *                           message.properties e preencher template de parâmetros dinamicamente.
     * @author Luciano Brito
     * @see <a href="https://github.com/lucianobritodev">github.com/lucianobritodev</a>
     */
    protected String getMessage(final String keyMessageTemplate, final Object... parameterMessage) {
        return messageSource.getMessage(keyMessageTemplate, parameterMessage, LocaleContextHolder.getLocale());
    }

}
