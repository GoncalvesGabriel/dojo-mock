package br.com.dojos.dojomock.services;

/**
 * @author vitor.alves
 */
public interface Validator<T> {

    void validar(T dado);

}
