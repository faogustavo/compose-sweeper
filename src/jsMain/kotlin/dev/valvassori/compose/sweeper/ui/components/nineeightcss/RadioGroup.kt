package dev.valvassori.compose.sweeper.ui.components.nineeightcss

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.name
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Legend
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Fieldset as ComposeFieldSet

/**
 * <fieldset>
 *   <legend>Today's mood</legend>
 *   <div class="field-row">
 *     <input id="radio13" type="radio" name="fieldset-example2">
 *     <label for="radio13">Claire Saffitz</label>
 *   </div>
 *   <div class="field-row">
 *     <input id="radio14" type="radio" name="fieldset-example2">
 *     <label for="radio14">Brad Leone</label>
 *   </div>
 *   <div class="field-row">
 *     <input id="radio15" type="radio" name="fieldset-example2">
 *     <label for="radio15">Chris Morocco</label>
 *   </div>
 *   <div class="field-row">
 *     <input id="radio16" type="radio" name="fieldset-example2">
 *     <label for="radio16">Carla Lalli Music</label>
 *   </div>
 * </fieldset>
 */

@Composable
fun RadioGroup(
    setName: String,
    value: String,
    options: List<Pair<String, String>>,
    onSelect: (String) -> Unit,
    legend: String? = null,
) {
    ComposeFieldSet {
        if (legend != null) Legend { Text(legend) }
        options.forEach { (key, text) ->
            Div(attrs = { classes("field-row") }) {
                Input(
                    type = InputType.Radio,
                    attrs = {
                        id("radio-$key")
                        name(setName)
                        checked(value == key)
                        onChange { if (it.value) onSelect(key) }
                    }
                )
                Label(forId = "radio-$key") { Text(text) }
            }
        }
    }
}
