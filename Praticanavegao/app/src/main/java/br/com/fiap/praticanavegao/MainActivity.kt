package br.com.fiap.praticanavegao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.fiap.navegandoentretelas.sreens.LoginScreen
import br.com.fiap.navegandoentretelas.sreens.MenuScreen
import br.com.fiap.navegandoentretelas.sreens.PedidosScreen
import br.com.fiap.navegandoentretelas.sreens.PerfilScreen
import br.com.fiap.praticanavegao.ui.theme.PraticaNavegaçãoTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.composable
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PraticaNavegaçãoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val controle = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = controle,
                        startDestination = "login"
                    ){
                        composable  (route = "login" )  { LoginScreen(controle) }

                        composable  ( route = "menu" )   { MenuScreen(controle) }

                        composable(
                            route = "pedidos?numero={numero}",
                            arguments = listOf(navArgument(name = "numero"){
                                defaultValue = "pedido não registrado"
                            })
                        )
                        {
                            PedidosScreen(controle, it.arguments?.getString("numero")!!)
                        }
                        composable  (
                            route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument(name = "nome"){
                                    type = NavType.StringType
                                },
                                navArgument(name = "idade"){
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val nome = it.arguments?.getString("nome")
                            val idade = it.arguments?.getInt("idade")
                            PerfilScreen(controle, nome!!, idade!!)
                        }
                    }
                }
            }
        }
    }
}

