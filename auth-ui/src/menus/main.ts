import Menu from './menu'

export default {
  home: new Menu("home", "/", "home", "首页 Home", []),
  account: new Menu("account", "/personal-info", "person", "个人信息 Account personal information", []),
  clients: new Menu("clients", "/clients", "apps", "应用 Client", [])
}


