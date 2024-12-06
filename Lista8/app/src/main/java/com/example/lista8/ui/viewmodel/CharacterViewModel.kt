import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.lista8.models.Character
import com.example.lista8.models.characterList

class CharacterViewModel : ViewModel() {
    private val _characters = mutableStateListOf<Character>().apply {
        addAll(characterList)
    }
    val characters: List<Character> = _characters

    fun toggleFavorite(character: Character) {
        val index = _characters.indexOfFirst { it.id == character.id }
        if (index != -1) {
            val updatedCharacter = _characters[index].copy(isFavorite = !character.isFavorite)
            _characters[index] = updatedCharacter
        }
    }

    fun getCharacterById(id: Int): Character? {
        return _characters.find { it.id == id }
    }
}
