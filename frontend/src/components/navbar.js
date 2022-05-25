import React from 'react'
import NavBarItem from './navbaritem'

export default function NavBar() {
    return (
      <div className="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
          <div className="container">
              <a href="https://bootswatch.com/" className="navbar-brand">Minhas Finanças</a>
              <button className="navbar-toggler" 
                type="button" data-toggle="collapse" 
                data-target="#navbarResponsive" 
                aria-controls="navbarResponsive" 
                aria-expanded="false" aria-label="Toggle navigation">
                  <span className="navbar-toggler-icon"></span>
              </button>
              <div className="collapse navbar-collapse" id="navbarResponsive">
                  <ul className="navbar-nav">
                      <NavBarItem 
                        link='/'
                        label='Home'
                      />
                      <NavBarItem 
                        link='/cadastro-usuario'
                        label='Usuários'
                      />
                      <NavBarItem 
                        link='/lancamento'
                        label='Lançamentos'
                      />
                      <NavBarItem 
                        link='/login'
                        label='Login'
                      />
                  </ul>

              </div>
          </div>
      </div>
  )
}