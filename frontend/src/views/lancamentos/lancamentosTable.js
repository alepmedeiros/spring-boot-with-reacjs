import React from 'react'
import currencyFormatter from 'currency-formatter';
import { Button } from '../../components/button';

const LancamentosTable = (props) => {
    const rows = props.lancamentos.map(lancamento => {
        return (
            <tr key={lancamento.id}>
                <td>{lancamento.descricao}</td>
                <td>{currencyFormatter.format(lancamento.valor, {locale: 'pt-BR'})}</td>
                <td>{lancamento.tipo}</td>
                <td>{lancamento.mes}</td>
                <td>{lancamento.status}</td>
                <td>
                    <Button 
                        type='primary'
                        icon='edit'
                        click={e => props.editAction(lancamento.id)}
                    />  
                    <Button 
                        type='danger'
                        icon='trash'
                        click={e => props.deleteAction(lancamento)}
                    />
                </td>
            </tr>
        )
    });

    return (
        <div className="bs-component">
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Descrição</th>
                        <th scope="col">Valor</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Mês</th>
                        <th scope="col">Situação</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {rows}
                </tbody>
            </table>
        </div>
    )
}

export default LancamentosTable;