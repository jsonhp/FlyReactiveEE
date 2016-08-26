import {bootstrap, Component, View, NgIf} from 'angular2/angular2';

//Protected-Content Component
@Component({
  selector: 'protected-component'
})
@View({
  templateUrl: 'app/views/component.html'
})
class ProtectedComponent{
}

//Main Component
@Component({
  selector: 'main'
})
@View({
  templateUrl: 'app/views/main.html',
  directives:[ProtectedComponent,NgIf]
})
class Main{
  isLogged:boolean;
  constructor(){
    this.isLogged = false;
  }
  login(username, password){
    if(username.value =="test" && password.value=="test"){
      this.isLogged = true;
    }
  }
  logout(){
    this.isLogged = false;
  }
}

bootstrap(Main);