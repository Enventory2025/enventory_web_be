package site.enventory.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import site.enventory.constant.Provider

@WritingConverter
class ProviderWritingConverter : Converter<Provider, String> {
    override fun convert(source: Provider): String = source.name
}

@ReadingConverter
class ProviderReadingConverter : Converter<String, Provider> {
    override fun convert(source: String): Provider = Provider.valueOf(source)
}
