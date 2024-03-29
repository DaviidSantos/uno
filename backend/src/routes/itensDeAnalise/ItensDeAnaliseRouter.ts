import express from "express";
import { cadastrarItensDeAnalise, listarItensDeAnalise, listarItensDeAnalisePorSolicitacao, procurarItemDeAnalisePorId } from "../../controllers/itensDeAnalise/ItensDeAnaliseController";

export default (router : express.Router) => {
    router.post('/itens-de-analise', cadastrarItensDeAnalise)
    router.get('/itens-de-analise', listarItensDeAnalise)
    router.get('/itens-de-analise/:id', procurarItemDeAnalisePorId)
    router.get('/itens-de-analise/solicitacao/:id', listarItensDeAnalisePorSolicitacao)
}