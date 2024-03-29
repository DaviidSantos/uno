import express from 'express'
import { cadastrarEnsaios, inicializarEnsaio, listarEnsaiosPorItemDeAnalise, procurarEnsaioPorId, resultadoEnsaio } from '../../controllers/ensaios/EnsaiosController'

export default (router : express.Router) => {
    router.post('/ensaios', cadastrarEnsaios)
    router.get('/ensaios/item-de-analise/:id', listarEnsaiosPorItemDeAnalise)
    router.get('/ensaios/:id', procurarEnsaioPorId)
    router.patch("/ensaios/inicializar/:id", inicializarEnsaio)
    router.patch('/ensaios/resultado/:id', resultadoEnsaio)
}